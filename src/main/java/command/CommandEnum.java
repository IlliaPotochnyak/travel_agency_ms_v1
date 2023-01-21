package command;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },

    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    LIST_TOUR {
        {
            this.command = new ListTourCommand();
        }
    },
    ADD_TOUR {
        {
            this.command = new AddTourCommand();
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
