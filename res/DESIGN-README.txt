
Stock-Market Simulator using MVC Architecture:

Model:

The model is the structure of our database i.e. User information, number of portfolios one user has,
number of stocks one user has bought and kept in which profile.

The model also has methods to buyStock that adds a particular stock to a particular portfolio, it
also has methods to view a portfolio composition and get the total basis and evaluation of a
portfolio.

Controller:

The controller here acts as a liaison between the view and model. The controller gets the input
in string format from the view, decodes the string and calls the appropriate methods
in the model.

View:

The view is the medium through which the user interacts with the software. View here takes input
from the user and passes the input to the controller. The controller fetches data from the model and
responds with the appropriate result that is displayed the in the console by view.