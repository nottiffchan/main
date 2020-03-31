package tatracker.logic.parser.session;

import static tatracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tatracker.logic.parser.Prefixes.DATE;
import static tatracker.logic.parser.Prefixes.MODULE;
import static tatracker.logic.parser.Prefixes.SESSION_TYPE;

import java.util.ArrayList;
import java.util.List;

import tatracker.logic.commands.session.AddSessionCommand;
import tatracker.logic.commands.session.FilterSessionCommand;
import tatracker.logic.parser.ArgumentMultimap;
import tatracker.logic.parser.ArgumentTokenizer;
import tatracker.logic.parser.Parser;
import tatracker.logic.parser.ParserUtil;
import tatracker.logic.parser.exceptions.ParseException;
import tatracker.model.session.SessionPredicate;


/**
 * Parses input arguments and creates a new FilterSessionCommand object
 */
public class FilterSessionCommandParser implements Parser<FilterSessionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterSessionCommand
     * and returns a FilterSessionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterSessionCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, DATE,
                MODULE, SESSION_TYPE);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddSessionCommand.DETAILS.getUsage()));
        }

        List<String> argsList = new ArrayList<>();

        if (argMultimap.getValue(DATE).isPresent()) {
            argsList.add(ParserUtil.parseDate(argMultimap.getValue(DATE).get()).toString());
        }

        if (argMultimap.getValue(MODULE).isPresent()) {
            argsList.add((ParserUtil.parseValue(argMultimap.getValue(MODULE).get())));
        }

        if (argMultimap.getValue(SESSION_TYPE).isPresent()) {
            argsList.add((
                    ParserUtil.parseSessionType(argMultimap.getValue(SESSION_TYPE).get()).toString()));
        }

        return new FilterSessionCommand(new SessionPredicate(argsList));
    }
}

