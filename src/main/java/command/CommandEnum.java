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
    DELETE_TOUR {
        {
            this.command = new DeleteTourCommand();
        }
    },
    EDIT_TOUR {
        {
            this.command = new EditTourCommand();
        }
    },
    TOUR_PAGE {
        {
            this.command = new TourPageCommand();
        }
    },
    RECEIPT_LIST {
        {
            this.command = new ReceiptListCommand();
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
