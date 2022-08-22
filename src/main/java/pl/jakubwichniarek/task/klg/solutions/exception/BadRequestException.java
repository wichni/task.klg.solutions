package pl.jakubwichniarek.task.klg.solutions.exception;

public class BadRequestException extends RuntimeException {

  public BadRequestException(String message) {
    super(message);
  }
}
