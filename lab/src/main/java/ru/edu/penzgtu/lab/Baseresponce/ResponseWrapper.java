package ru.edu.penzgtu.lab.Baseresponce;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Обертка для ответа")
public class ResponseWrapper<T> {
    @Schema(description = "Флаг для успешности выполнения запроса")
    private boolean success;

    @Schema(description = "Тело запроса")
    private T body;

    @Schema(description = "Описание ошибки")
    private ErrorDto error;
}
