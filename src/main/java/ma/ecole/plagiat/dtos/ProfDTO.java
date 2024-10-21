package ma.ecole.plagiat.dtos;


import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ProfDTO(
        String name,
        String email,
        String department
)  {}
