package com.juandiegoh.grails_rollback

import org.springframework.transaction.support.DefaultTransactionDefinition

class MyProcessor {

    MyTransactionalService myTransactionalService
    MyHalfTransactionalService myHalfTransactionalService

    def processRollback() {
        def transactionDefinition = new DefaultTransactionDefinition()
        transactionDefinition.setName("MY ROLLBACK TRANSACTION")
        log.info("Starting Transaction")
        MyDomain.withTransaction(transactionDefinition) {
            try {
                myTransactionalService.createMyDomain("Learning Rollback")
                myTransactionalService.transactionalMethod()
            } catch(e) {
                log.error("I catch the Exception", e)
            }
        }
        log.info("Finished Transaction")
    }

    def processWithoutRollback() {
        def transactionDefinition = new DefaultTransactionDefinition()
        transactionDefinition.setName("MY COMMIT TRANSACTION")
        log.info("Starting Transaction")
        MyDomain.withTransaction(transactionDefinition) {
            try {
                myHalfTransactionalService.createMyDomain("Learning Rollback")
                myHalfTransactionalService.nonTransactionalMethod()
            } catch(e) {
                log.error("I catch the Exception", e)
            }
        }
        log.info("Finished Transaction")
    }
}
