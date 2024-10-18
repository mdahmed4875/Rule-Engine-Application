/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ruleengine;

/**
 *
 * @author Asus
 */
// RuleEngine.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class RuleEngine {
    private DatabaseConnection dbConnection;

    public RuleEngine() {
        dbConnection = new DatabaseConnection();
    }

    public Node create_rule(String ruleString) {
        return parse(ruleString);
    }

    public Node combine_rules(Node[] rules) {
        // Combining rules could involve creating a new node with AND/OR logic
        // This example combines using AND
        Node root = new Node("operator", null, null, "AND");
        root.left = rules[0];
        root.right = rules[1];
        return root;
    }

    public boolean evaluate_rule(Node ast, Map<String, Object> userData) {
        if (ast == null) {
            return false;
        }
        switch (ast.type) {
            case "operand":
                return evaluateCondition(ast.value, userData);
            case "operator":
                return evaluateOperator(ast, userData);
        }
        return false;
    }

    public boolean evaluateCondition(String condition, Map<String, Object> userData) {
    String[] parts = condition.trim().split(" ");
    
    // Check if the condition is in the correct format (3 parts: operand, operator, value)
    if (parts.length != 3) {
        System.err.println("Invalid condition: " + condition);
        return false;
    }

    String operand = parts[0];  // e.g., "age"
    String operator = parts[1]; // e.g., ">"
    String value = parts[2];    // e.g., "30"

    // Fetch the corresponding user data value (e.g., userData.get("age"))
    Object userValue = userData.get(operand);

    if (userValue == null) {
        System.err.println("Unknown operand: " + operand);
        return false;
    }

    // Convert userValue to appropriate type for comparison
    if (userValue instanceof Integer) {
        int userIntValue = (int) userValue;
        int intValue = Integer.parseInt(value);

        switch (operator) {
            case ">":
                return userIntValue > intValue;
            case "<":
                return userIntValue < intValue;
            case "=":
                return userIntValue == intValue;
            default:
                System.err.println("Unknown operator: " + operator);
                return false;
        }
    } else if (userValue instanceof String) {
        String userStringValue = (String) userValue;

        // For string comparisons, we assume operator should be '=' for equality check
        if (operator.equals("=")) {
            return userStringValue.equals(value.replace("'", ""));
        } else {
            System.err.println("Unknown operator for string: " + operator);
            return false;
        }
    }

    System.err.println("Unhandled data type for operand: " + operand);
    return false;
}


    private boolean evaluateOperator(Node ast, Map<String, Object> userData) {
        boolean leftEval = evaluate_rule(ast.left, userData);
        boolean rightEval = evaluate_rule(ast.right, userData);
        return ast.value.equals("AND") ? leftEval && rightEval : leftEval || rightEval;
    }

    public void insertRule(String rule) {
        String insertSQL = "INSERT INTO rules (rule_string) VALUES (?)";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, rule);
            preparedStatement.executeUpdate();
            System.out.println("Rule inserted successfully: " + rule);
        } catch (SQLException e) {
            System.err.println("Error inserting rule: " + e.getMessage());
        }
    }

    public void close() {
        dbConnection.closeConnection();
    }

    // Method to parse the rule string into an AST
    private Node parse(String ruleString) {
        Stack<Node> stack = new Stack<>();
        String[] tokens = ruleString.split(" ");
        for (String token : tokens) {
            if (token.equals("AND") || token.equals("OR")) {
                Node right = stack.pop();
                Node left = stack.pop();
                stack.push(new Node("operator", left, right, token));
            } else {
                stack.push(new Node("operand", null, null, token));
            }
        }
        return stack.pop(); // Return the root of the AST
    }
}
