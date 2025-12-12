package de.seuhd.campuscoffee.domain.model.objects;

import lombok.Builder;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.time.LocalDateTime;

/**
 * Domain record that stores a review for a point of sale.
 * Reviews are approved once they received a configurable number of approvals.
 * This is an immutable value object - use the builder or toBuilder() to create modified copies.
 * Records provide automatic implementations of equals(), hashCode(), toString(), and accessors.
 * <p>
 * We validate the fields in the API layer based on the DTOs, so no validation annotations are needed here.
 *
 * @param id          the unique identifier; null when the review has not been created yet
 * @param createdAt   timestamp set on review creation
 * @param updatedAt   timestamp set on review creation and update
 * @param posId       the unique identifier of the POS that the review belongs to
 * @param authorId    the unique identifier of the review author
 * @param review      the review content
 * @param approved    approval status of the review
 */
@Builder(toBuilder = true)
public record Review(
        @Nullable Long id, // null when the review has not been created yet
        //TODO: Implement review domain model.
        @Nullable LocalDateTime createdAt, // is null when using DTO to create a new review
        @Nullable LocalDateTime updatedAt, // is set when creating or updating a review
        @NonNull Long posId,
        @NonNull Long authorId,
        @NonNull String review,
        @NonNull Integer approvalCount, // is updated by the domain module
        @NonNull Boolean approved, // is determined by the domain module
        @NonNull Pos pos,
        @NonNull User author
) implements DomainModel<Long> {
    public Review {
        // If posId not set, get from pos
        if (posId == null && pos != null) {
            posId = pos.getId();
        }
        // If authorId not set, get from author
        if (authorId == null && author != null) {
            authorId = author.getId();
        }
    }
    @Override
    public Long getId() {
        return id;
    }
}