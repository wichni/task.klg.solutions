package pl.jakubwichniarek.task.klg.solutions.converter;

public abstract class Converter<Entity, DTO> {

  public abstract Entity convertToEntity(DTO dto);

  public abstract DTO convertToDTO(Entity entity);
}
