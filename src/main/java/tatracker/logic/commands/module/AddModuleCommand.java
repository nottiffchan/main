package tatracker.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static tatracker.logic.parser.Prefixes.MODULE;
import static tatracker.logic.parser.Prefixes.MODULE_NAME;

import java.util.List;

import tatracker.logic.commands.Command;
import tatracker.logic.commands.CommandDetails;
import tatracker.logic.commands.CommandResult;
import tatracker.logic.commands.CommandResult.Action;
import tatracker.logic.commands.CommandWords;
import tatracker.logic.commands.exceptions.CommandException;
import tatracker.model.Model;
import tatracker.model.module.Module;

/**
 * Adds a module to the TA-Tracker.
 */
public class AddModuleCommand extends Command {

    public static final CommandDetails DETAILS = new CommandDetails(
            CommandWords.MODULE,
            CommandWords.ADD_MODEL,
            "Adds a module into TA-Tracker.",
            List.of(MODULE, MODULE_NAME),
            List.of(),
            MODULE, MODULE_NAME
    );

    public static final String MESSAGE_SUCCESS = "New Module added: %s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the TA-Tracker";
    private static final String INVALID_MODULE_CODE = "You can't use that as a module code.";
    private static final String INVALID_MODULE_NAME = "You can't use that for the module name";

    private final Module toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Module}
     */
    public AddModuleCommand(Module module) {
        requireNonNull(module);
        toAdd = module;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (toAdd.getIdentifier().isBlank()) {
            throw new CommandException(INVALID_MODULE_CODE);
        }

        if (toAdd.getName().isBlank()) {
            throw new CommandException(INVALID_MODULE_NAME);
        }

        if (model.hasModule(toAdd.getIdentifier())) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.addModule(toAdd);

        model.updateFilteredGroupList(toAdd.getIdentifier());

        model.setFilteredStudentList();

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), Action.GOTO_STUDENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddModuleCommand // instanceof handles nulls
                && toAdd.equals(((AddModuleCommand) other).toAdd));
    }
}
