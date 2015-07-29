package com.juandiegoh.grails_rollback

import grails.test.spock.IntegrationSpec

class MyProcessorIntegrationSpec extends IntegrationSpec {

    static transactional = false

    MyTransactionalService myTransactionalService
    MyHalfTransactionalService myHalfTransactionalService

    def myProcessor

    def setup() {
        myProcessor = new MyProcessor(
                myTransactionalService: myTransactionalService,
                myHalfTransactionalService: myHalfTransactionalService)
    }

    def cleanup() {
        MyDomain.findAll()*.delete(flush: true)
    }

    void "process will rollback"() {
        when:
        myProcessor.processRollback()

        then:
        null == MyDomain.findByName("Learning Rollback")
    }

    void "process will not rollback"() {
        when:
        myProcessor.processWithoutRollback()

        then:
        null != MyDomain.findByName("Learning Rollback")
    }
}
