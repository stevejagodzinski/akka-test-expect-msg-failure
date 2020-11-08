package com.github.stevejagodzinski.akkatest;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import org.testng.annotations.Test;

import java.util.Objects;

public class FailingTest {

    @Test
    void makeFailure() {
        // Arrange
        final Object expected = new TestMessage("expected");
        final Object actual = new TestMessage("actual");

        final ActorSystem system = ActorSystem.create();
        final TestKit testKit = new TestKit(system);

        testKit.getRef().tell(actual, ActorRef.noSender());

        // Act
        testKit.expectMsg(expected);
    }

    private static class TestMessage {
        private final String message;

        private TestMessage(String message) {
            this.message = message;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestMessage that = (TestMessage) o;
            return Objects.equals(message, that.message);
        }

        @Override
        public int hashCode() {
            return Objects.hash(message);
        }

        @Override
        public String toString() {
            return "TestMessage{" +
                    "message='" + message + '\'' +
                    '}';
        }
    }

}
