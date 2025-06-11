package com.example.ungdungdocdulieu;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class EmployeeHandler extends DefaultHandler {

    private ArrayList<Employee> employees = new ArrayList<>();
    private Employee employee;
    private StringBuilder data = new StringBuilder();

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("employee")) {
            employee = new Employee();
            employee.setId(attributes.getValue("id"));
            employee.setTitle(attributes.getValue("title"));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (employee != null) {
            if (qName.equals("name")) {
                employee.setName(data.toString().trim());
            } else if (qName.equals("phone")) {
                employee.setPhone(data.toString().trim());
            } else if (qName.equals("employee")) {
                employees.add(employee);
            }
        }
        data.setLength(0); // reset bộ đệm
    }
}


