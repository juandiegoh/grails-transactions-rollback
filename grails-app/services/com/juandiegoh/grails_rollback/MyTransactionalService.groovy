package com.juandiegoh.grails_rollback

import grails.transaction.Transactional

@Transactional
class MyTransactionalService {

    def createMyDomain(String name) {
        def myDomain = new MyDomain(name: name)
        myDomain.save(failOnError: true)
    }

    def transactionalMethod() {
        throw new RuntimeException("This method is transactional, I will rollback everything")
    }
}
