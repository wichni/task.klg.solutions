package pl.jakubwichniarek.task.klg.solutions.exception.errormessage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
public class ErrorMessage {
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime time;
  private HttpStatus status;
  private String message;

  private ErrorMessage() {
    time = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
  }

  public ErrorMessage(HttpStatus status, String message) {
    this();
    this.status = status;
    this.message = message;
  }
}
