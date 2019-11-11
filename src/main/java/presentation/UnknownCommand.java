package presentation;

import exception.CommandException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The purpose of UnknownCommand is to throw a CommandException
 *
 * Pattern: Command
 *
 * @author carol
 */
public class UnknownCommand extends Command {

     /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws CommandException
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) 
            throws CommandException {
        throw new CommandException("Unknown command. Please contact IT.");
    }

}
