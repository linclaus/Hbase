package com.seu.linclaus;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseTest {
	public static void main(String[] args) {
		try {
			Configuration conf = HBaseConfiguration.create();
			conf.set("hbase.zookeeper.quorum", "192.168.1.113:2181,192.168.1.114:2181,192.168.1.115:2181");
			HBaseAdmin admin = new HBaseAdmin(conf);
			TableName name = TableName.valueOf("nvshen");
			HTableDescriptor desc = new HTableDescriptor(name);
			HColumnDescriptor base_info = new HColumnDescriptor("base_info");
			HColumnDescriptor extra_info = new HColumnDescriptor("extra_info");
			base_info.setMaxVersions(5);
			desc.addFamily(base_info);
			desc.addFamily(extra_info);
			admin.createTable(desc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	static class Information implements Comparable<Information> {
		String source;
		int match_count;

		public Information(String source, int match_count) {
			this.source = source;
			this.match_count = match_count;
		}

		@Override
		public int compareTo(Information o) {
			return this.match_count < o.match_count ? 1 : -1;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return source+":"+match_count+"\n";
		}

	}
}
