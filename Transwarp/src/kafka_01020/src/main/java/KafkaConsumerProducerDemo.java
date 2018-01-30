package kafka_01020.src.main.java;/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class KafkaConsumerProducerDemo {
    public static void main(String[] args) {
        /**
         * Create a new topic
         */
//        TopicCommand.main(new String[]{"--create","--topic","test0828","--partitions","3","--replication-factor","1","--zookeeper","172.16.140.27:2181"});

        /**
         * Alter the number of partitions, replica assignment, and/or configuration for the topic
         */
//        TopicCommand.main(new String[]{"--alter","--zookeeper","172.16.140.27:2181","--topic","test0828","--partitions","5"});

        /**
         * List all available topics.
         */
//        TopicCommand.main(new String[]{"--list","--zookeeper","172.16.140.27:2181"});
        /**
         * List details fro the given topics
         */
//       TopicCommand.main(new String[]{"--describe","--zookeeper","172.16.140.27:2181","--topic","test0828"});

//        boolean isAsync = args.length == 0 || !args[0].trim().equalsIgnoreCase("sync");
//        Producer producerThread = new Producer(KafkaProperties.TOPIC, isAsync);
//        System.out.println(KafkaProperties.TOPIC);
//        producerThread.start()


//        Consumer consumerThread = new Consumer(KafkaProperties.TOPIC);
//
//        consumerThread.start();

    }
}
