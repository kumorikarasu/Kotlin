package com.ryougi.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.oss.driver.api.core.CqlSession;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent
import io.micronaut.discovery.event.ServiceReadyEvent;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import com.ryougi.hello.HelloOM;

/**
 * Execute some Action and application startup.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
@Singleton
class ApplicationStartup: ApplicationEventListener<StartupEvent> {
  companion object {
      @JvmField val LOG = LoggerFactory.getLogger(ApplicationStartup::class.java)
  }

  @Inject lateinit var cqlSession: CqlSession;
    
  override fun onApplicationEvent(event: StartupEvent) {
        LOG.info("Startup Initialization");
        HelloOM().createTable(cqlSession);
        LOG.info("+ Table TodoItems created if needed.");
        LOG.info("[OK]");
    }

}
