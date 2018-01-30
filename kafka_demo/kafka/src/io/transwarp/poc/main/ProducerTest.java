package io.transwarp.poc.main;

import io.transwarp.poc.utils.RandomInt;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerTest {
	public static Producer<String, String> producer;
	public static RandomInt ri1;
	public static Properties props = new Properties();

	static {
		props.put("bootstrap.servers", "172.16.2.67:9092");
		props.put("acks", "all");
		props.put("retries ", 1);
		props.put("buffer.memory", 33554432);
		props.put("batch.size", 1000);
		props.put("key.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");

	}

	private List<Worker> threads = new ArrayList<Worker>();

	public static void main(String[] args) {
		int seconds = 10;

		ProducerTest pt = new ProducerTest();
		pt.kafkaWrite(seconds);
	}

	public void kafkaWrite(int second) {
		for (int i = 0; i < 1; i++) {
			producer = new KafkaProducer<String, String>(props);
			threads.add(new Worker(i, second, producer));
		}

		for (Worker w : threads) {
			w.start();
		}

	}

	class Worker extends Thread {

		public boolean stopped;
		public String index;
		public int second;
		public Producer<String, String> producer;

		public Worker(int i, int seconds, Producer<String, String> prod) {
			stopped = false;
			index = String.valueOf(i);
			second = seconds;
			producer = prod;
		}

		int a[] = { 0}; //, 1, 2, 5, 4, 3, 6, 7, 8, 9 };

		public void run() {

			while (!stopped) {

				for (int i : a) {
					long startTime = System.currentTimeMillis();

					String time = getTime(i, "2017-09-28 08:00:");

					for (int j = 1; j <= 5000; j++) {
						StringBuffer record = new StringBuffer();
						ri1 = new RandomInt(10010);
						//record.append(i).append(index).append(getRecordKey(j))
						 record.append("4-NOT SPECIFIED")
								.append(",").append(index).append(",")
								.append(i).append(",").append("1").append(",")
								.append(time).append(",")
								.append(ri1.getColumnData()).append(",")
								.append(getTimeSSS());

						producer.send(new ProducerRecord<String, String>("xj",
								index, record.toString()));
					}
					long endTime = System.currentTimeMillis();

					long cost = endTime - startTime;
					System.out.println("第  " + i + " 秒写入数据： ");
					System.out.println("Partition " + index
							+ " 成功写入75000 条数据：耗时 " + cost + " 毫秒");

					if (cost < 1000) {
						try {
							System.out
									.println("sleep " + (1000 - cost) + " 毫秒");
							System.out.println();
							Thread.sleep(1000 - cost);

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					stopped = true;
				}
			}
		}

		private String getTime(int i, String base) {
			if (i < 10) {
				return base + "0" + i;
			} else {
				return base + i;
			}

		}

	}

	public static String getRecordKey(int index) {
		String base = "0000000000";
		int length = String.valueOf(index).length();

		return base.substring(length) + index;
	}

	public Long getTimeSSS() {
		return System.currentTimeMillis();
	}

}
