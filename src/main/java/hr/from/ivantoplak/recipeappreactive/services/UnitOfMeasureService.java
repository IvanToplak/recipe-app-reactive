package hr.from.ivantoplak.recipeappreactive.services;

import hr.from.ivantoplak.recipeappreactive.commands.UnitOfMeasureCommand;
import reactor.core.publisher.Flux;

public interface UnitOfMeasureService {

    Flux<UnitOfMeasureCommand> listAllUoms();
}
