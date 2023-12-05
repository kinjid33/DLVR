package mscreative.example.dlvr.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mscreative.example.dlvr.enums.RegisterStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse
{
    @Enumerated(EnumType.STRING)
    private RegisterStatus status;
}
