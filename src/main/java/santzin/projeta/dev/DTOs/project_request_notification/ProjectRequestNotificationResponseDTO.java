package santzin.projeta.dev.DTOs.project_request_notification;

import santzin.projeta.dev.DTOs.project_request.ProjectRequestResponseDTO;
import santzin.projeta.dev.model.enums.TypeProjectRequestNotification;

import java.time.LocalDate;

public record ProjectRequestNotificationResponseDTO(
        Long id,
        String  projectTitle,
        String projectSlug,
        String usernameSender,
        String usernameReceiver,
        ProjectRequestResponseDTO projectRequest,
        boolean read,
        TypeProjectRequestNotification type,
        LocalDate createdAt

) {
}
