package de.seuhd.campuscoffee.api.dtos;

import de.seuhd.campuscoffee.domain.model.objects.Pos;
import de.seuhd.campuscoffee.domain.model.objects.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.time.LocalDateTime;

/**
 * DTO record for POS metadata.
 */
@Builder(toBuilder = true)
public record ReviewDto (
        @Nullable Long id, // id is null when creating a new task
        // TODO: Implement ReviewDto
        @Nullable LocalDateTime createdAt, // is null when using DTO to create a new review
        @Nullable LocalDateTime updatedAt, // is set when creating or updating a review

        @NotNull
        @NonNull Long posId,

        @NotNull
        @NonNull Long authorId,

        @NotBlank(message = "Review cannot be empty.")
        @NonNull String review,

        @NotNull
        @NonNull Boolean approved,

        @NotNull
        @NotNull Pos pos,

        @NotNull
        @NonNull User author
        ) implements Dto<Long> {
    @Override
    public @Nullable Long getId() {
        return id;
    }
}
