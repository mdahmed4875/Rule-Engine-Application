<h1>Rule Engine Application</h1>
<h2>Overview</h2>
<p>This project is a Rule Engine Application built in Java. It allows users to define and evaluate rules based on user attributes like age, department, salary, and experience. The rules are represented using Abstract Syntax Trees (AST) and stored in a database for persistence. The application dynamically modifies and evaluates the rules to determine user eligibility or conditions.</p>
<h2>Features</h2>
<ul>
  <li><b>Dynamic Rule Insertion</b>: Users can insert rules through a command-line interface.</li>
  <li><b>Rule Evaluation</b>: Evaluates rules against user-provided data.</li>
  <li><b>Abstract Syntax Tree (AST) Representation<b/>: Parses and represents rules using AST for efficient evaluation.</li>
  <li><b>Database Persistence<b/>: Stores the rules in a database using JDBC.</li>
</ul>
<h2>Prerequisites</h2>
<p>To run this project, you will need:</p>
<ul>
  <li><b>Java 8 or higher<b/>: Ensure you have Java Development Kit (JDK) installed.</li>
  <li><b>NetBeans IDE<b/>: The project was developed using NetBeans but can be run in other IDEs like Eclipse or IntelliJ.</li>
  <li><b>MySQL or any SQL Database<b>: The application uses a SQL database to store rules. You can configure the DatabaseConnection class to match your database setup.</li>
  <li><b>JDBC Driver<b/>: A compatible JDBC driver to connect Java with your database.</li>
</ul>
<h2>Project Structure</h2>
<h3>Main Class</h3>
<p>The Main class is responsible for:</p>
<ul>
  <li>Reading a rule from user input.</li>
  <li>Inserting the rule into the database.</li>
  <li>Creating and evaluating the rule against user data.</li>
</ul>
<h3>RuleEngine Class</h3>
<p>The RuleEngine class handles:</p>
<ul>
  <li>Parsing the rule into an AST using the parse() method.</li>
  <li>Evaluating the AST with the provided user data.</li>
  <li>Inserting rules into the database using JDBC.</li>
  <li>Combining multiple rules using logical operators like AND/OR.</li>
</ul>
<h3>Node Class</h3>
<p>The Node class represents a node in the AST. It stores:</p>
<ul>
  <li>Type (operand or operator).</li>
  <li>Left and right child nodes (for operators).</li>
  <li>The value (condition or operator).</li>
</ul>
<h3>DatabaseConnection Class</h3>
<p>Handles the database connection and disconnection using JDBC.</p>
<h2>How to Run the Application</h2>
<h4>Clone the Repository:</h4>
<p><code>git clone <https://github.com/mdahmed4875/Rule-Engine-Application.git></code></p>


