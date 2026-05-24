package com.swachhdrishti.swachh_drishti.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name= "reports")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //for waiting of actual call
    @JoinColumn(name = "user_Id", nullable = false) //don't allow hibernate to guess
    private User user;


    //Location

    private String address;
    private String Description;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    // report content
    @Column(name ="image_url")
    private String imageUrl;

    // AI ANALYSIS OUTPUT

    @Column( name ="severity_score")
    private Integer severityScore;  //1 to 5 , set by VisionService

    //
    @Enumerated(EnumType.STRING)
    @Column(name = "garbage_type")

    private GarbageType garbageType;

    // ai_confidence — the score Vision API returns for its top label (0.0 to 1.0)
    // Example: label "waste" with confidence 0.94 → stored here
    // Used to decide isGarbage check: if confidence < 0.7, don't award coins
    @Column(name = "ai_confidence")
    private Double aiConfidence;

    // ai_labels — raw comma-separated labels from Vision API
    // Example: "waste,plastic,pollution,litter"
    // WHY store this: debugging + evidence for your research paper
    // length = 500 — Vision API can return many labels, default 255 is not enough
    @Column(name = "ai_label", length=500)
    private  String aiLabels;


    //LifeCycle
    // @Builder.Default — REQUIRED when using @Builder with default field values
    // WHY: @Builder ignores Java field initializers (= Status.PENDING)
    // unless you add @Builder.Default. Without it, status would be null when built.
    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false)
    private Status status = Status.PENDING;

    @Builder.Default
    @Column( name ="coins_awarded", nullable = false)
    private Integer coinsAwarded=0;

    // resolvedAt is null until a municipal officer marks it resolved
    // Set manually in ReportService when status is updated to RESOLVED
    @Column(name ="resolved_at")
    private LocalDateTime resolvedAT;

    @Column(name = "assigned_worker_id")
    private Long assignedWorkerId;

    //Audit TimeStamps

    @CreationTimestamp
    @Column(name ="created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Enums - defined inside the entity, they belong to report

    public enum GarbageType{
        PLASTIC, ORGANIC, E_WASTE, MIXED
    }
    public enum Status{
        PENDING, IN_PROGRESS, RESOLVED, REJECTED
    }
}
