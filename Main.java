/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ruleengine;

/**
 *
 * @author Asus
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private RuleEngine ruleEngine;

    public Main() {
        ruleEngine = new RuleEngine();
        createUI();
    }

    private void createUI() {
        JFrame frame = new JFrame("Rule Engine UI");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(8, 2));

        // Rule input
        JLabel ruleLabel = new JLabel("Enter Rule:");
        JTextField ruleInput = new JTextField();
        frame.add(ruleLabel);
        frame.add(ruleInput);

        // User inputs
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageInput = new JTextField();
        frame.add(ageLabel);
        frame.add(ageInput);

        JLabel deptLabel = new JLabel("Department:");
        JTextField deptInput = new JTextField();
        frame.add(deptLabel);
        frame.add(deptInput);

        JLabel salaryLabel = new JLabel("Salary:");
        JTextField salaryInput = new JTextField();
        frame.add(salaryLabel);
        frame.add(salaryInput);

        JLabel expLabel = new JLabel("Experience:");
        JTextField expInput = new JTextField();
        frame.add(expLabel);
        frame.add(expInput);

        // Button to add rule and evaluate
        JButton evaluateButton = new JButton("Add Rule and Evaluate");
        JLabel resultLabel = new JLabel("Result: ");
        frame.add(evaluateButton);
        frame.add(resultLabel);

        // Button action to insert rule and evaluate
        evaluateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rule = ruleInput.getText();

                // Insert rule into the RuleEngine
                ruleEngine.insertRule(rule);
                Node ruleNode = ruleEngine.create_rule(rule);

                // Create user data map
                Map<String, Object> userData = new HashMap<>();
                try {
                    userData.put("age", Integer.parseInt(ageInput.getText()));
                    userData.put("department", deptInput.getText());
                    userData.put("salary", Integer.parseInt(salaryInput.getText()));
                    userData.put("experience", Integer.parseInt(expInput.getText()));
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input. Please enter correct values.");
                    return;
                }

                // Evaluate rule
                boolean result = ruleEngine.evaluate_rule(ruleNode, userData);
                resultLabel.setText("Result: " + (result ? "True" : "False"));
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
