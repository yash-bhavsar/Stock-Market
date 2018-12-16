
Stock-Market Simulator using MVC Architecture:

Model:

The model is the structure of our database i.e. User information, number of portfolios one user has,
number of stocks one user has bought and kept in which profile.

The model also has methods to buyStock that adds a particular stock to a particular portfolio, it
also has methods to view a portfolio composition and get the total basis and evaluation of a
portfolio. The model also offers the ability to save and load portfolios and strategies.

Controller:

The controller here acts as a liaison between the view and model. The controller gets the input
in string format from the view, decodes the string and calls the appropriate methods
in the model. Invalid dates are taken care of by asking the user to enter the date again. The controller is now solely responsible for taking data from the model and passing it to the view and vice versa.

View:

The view is the medium through which the user interacts with the software. View here takes input
from the user and passes the input to the controller. The controller fetches data from the model and
responds with the appropriate result that is displayed the in the console by view. In addition to this, a graph can also be plotted of how the portfolio changes over time. We also have two views now which implement the View interface. One is for the GUI and another for console view. Any new view can be added with minimal to no changes being made to the controller because the new view would implement the interface and take inputs from the controller.

Since the API hit rate slows down the application considerably, Caching is used to have the listing of the companies that are already bought by the user.
This improves the efficiency of the application.
