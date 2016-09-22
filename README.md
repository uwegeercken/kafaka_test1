First tests with Apache Kafka

Created a java class TestMessage that is used to serialize messages and sent to Kafka using a producer class.

A consumer class will read the message from Kafka and deserialize it to a TestMessage object.

Finally a class with a callback function will printout the fields of the TestMessage object.

Uwe Geercken - 2016-09-22

