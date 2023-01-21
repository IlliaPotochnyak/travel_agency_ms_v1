package command;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LIST_TOUR {
        {
            this.command = new ListTourCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    REGISTER {
        {
            this.command = new RegisterCommand();
        }
    };
        ActionCommand command;

        public ActionCommand getCurrentCommand() {
            return command;
        }

}
