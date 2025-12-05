package com.contable.domain;

import static com.contable.domain.BankAccountTestSamples.*;
import static com.contable.domain.CategoryTestSamples.*;
import static com.contable.domain.ContactTestSamples.*;
import static com.contable.domain.TransactionTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.contable.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TransactionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Transaction.class);
        Transaction transaction1 = getTransactionSample1();
        Transaction transaction2 = new Transaction();
        assertThat(transaction1).isNotEqualTo(transaction2);

        transaction2.setId(transaction1.getId());
        assertThat(transaction1).isEqualTo(transaction2);

        transaction2 = getTransactionSample2();
        assertThat(transaction1).isNotEqualTo(transaction2);
    }

    @Test
    void categoryTest() {
        Transaction transaction = getTransactionRandomSampleGenerator();
        Category categoryBack = getCategoryRandomSampleGenerator();

        transaction.setCategory(categoryBack);
        assertThat(transaction.getCategory()).isEqualTo(categoryBack);

        transaction.category(null);
        assertThat(transaction.getCategory()).isNull();
    }

    @Test
    void bankAccountTest() {
        Transaction transaction = getTransactionRandomSampleGenerator();
        BankAccount bankAccountBack = getBankAccountRandomSampleGenerator();

        transaction.setBankAccount(bankAccountBack);
        assertThat(transaction.getBankAccount()).isEqualTo(bankAccountBack);

        transaction.bankAccount(null);
        assertThat(transaction.getBankAccount()).isNull();
    }

    @Test
    void contactTest() {
        Transaction transaction = getTransactionRandomSampleGenerator();
        Contact contactBack = getContactRandomSampleGenerator();

        transaction.setContact(contactBack);
        assertThat(transaction.getContact()).isEqualTo(contactBack);

        transaction.contact(null);
        assertThat(transaction.getContact()).isNull();
    }
}
