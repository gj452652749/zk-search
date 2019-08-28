package com.zkyunso.searchengine.cloud;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class EngineStatus {

	private static final Logger logger = LoggerFactory.getLogger(EngineStatus.class);
	List<String> live_nodes_cache=new CopyOnWriteArrayList<String>();
	ZkClient zkClient=null;
	@Value("${zk.hosts}")
	String zkHosts;
	Random random = new Random();
//	@PostConstruct
	public void init() {
        zkClient = new ZkClient(zkHosts,10000,10000,new SerializableSerializer());  
        logger.info("The engine is started!"+zkHosts);  
        /** 
         * "/testUserNode" 监听的节点，可以是现在存在的也可以是不存在的 
         */ 
        resetStatus();
        zkClient.subscribeChildChanges("/live_nodes", new EngineListener());
	}
	@PreDestroy
	public void destory() {
		logger.info("engine has been closed!");
	}
	public List<String> getLiveNodes() {
		return live_nodes_cache;
	}
	public void updateStatus(List<String> live_nodes) {
		live_nodes_cache.clear();
		String host;
		for(String ele:live_nodes) {
			host="http://"+ele.replace("_", "/");
			live_nodes_cache.add(host);
		}
	}
	/**
	 * 从可用服务列表随机返回一个服务地址
	 * @return
	 */
	public String getSearchBaseUrl() {
		int size=live_nodes_cache.size();
		return live_nodes_cache.get((random.nextInt(size)));
	}
	public void resetStatus() {
		List<String> nodes=zkClient.getChildren("/live_nodes");
		updateStatus(nodes);
	}
	public class EngineListener implements IZkChildListener{
		@Override
		public void handleChildChange(String arg0, List<String> live_nodes) throws Exception {
			// TODO Auto-generated method stub
			updateStatus(live_nodes);
//			logger.info(arg0);
//			logger.info(live_nodes.toString());
		}

	}
}
