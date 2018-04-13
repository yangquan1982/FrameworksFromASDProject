package framework.common;

import java.text.DecimalFormat;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import shopping.model.Customer.Customer;

public class TransactionLogFunctor implements LogFunctor<String, Customer, Double> {
    private Logger logger;
    private StringBuffer sb;

    public TransactionLogFunctor() {
        logger = Logger.getLogger(TransactionLogFunctor.class.getName());
        PropertyConfigurator.configure("log4j.properties");
    }

    public void preMessage(String action) {
        logger.info("Action: " + action);
    }

    public void postMessage(Customer account, Double amount) {
        DecimalFormat df = new DecimalFormat("#####.00");
        sb = new StringBuffer("");
        sb.append("Account ID: " + account.getCustomerProfile().getId());
        sb.append(" | Name: " + account.getCustomerProfile().getFullName());
        sb.append(" | Account Type: " + account.getCustomerType());
        sb.append(" | BankCardNo: " + account.getCustomerProfile().getBankCardNo());
        logger.info(sb);
        logger.info("========================================");
    }
}
