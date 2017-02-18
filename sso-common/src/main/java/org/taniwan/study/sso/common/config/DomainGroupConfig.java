package org.taniwan.study.sso.common.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomainGroupConfig {

	private String domain;
	private SsoGroupTypeEnum group;

	private static final List<DomainGroupConfig> groupA = new ArrayList<>();
	private static final List<DomainGroupConfig> groupB = new ArrayList<>();
	
	private static final Map<String, List<DomainGroupConfig>> domainMap = new HashMap<>();
	
	static{
		initDomainGroup();
	}

	private static void initDomainGroup() {
		DomainGroupConfig domain1 = new DomainGroupConfig();
		domain1.setGroup(SsoGroupTypeEnum.A);
		domain1.setDomain(PropertyConfigHolder.getProperty("domain1"));
		DomainGroupConfig domain2 = new DomainGroupConfig();
		domain2.setGroup(SsoGroupTypeEnum.A);
		domain2.setDomain(PropertyConfigHolder.getProperty("domain2"));
		DomainGroupConfig domain3 = new DomainGroupConfig();
		domain3.setGroup(SsoGroupTypeEnum.A);
		domain3.setDomain(PropertyConfigHolder.getProperty("domain3"));
		groupA.add(domain1);
		groupA.add(domain2);
		groupA.add(domain3);
		domainMap.put(domain1.getDomain(), groupA);
		domainMap.put(domain2.getDomain(), groupA);
		domainMap.put(domain3.getDomain(), groupA);
		DomainGroupConfig domain4 = new DomainGroupConfig();
		domain4.setGroup(SsoGroupTypeEnum.B);
		domain4.setDomain(PropertyConfigHolder.getProperty("domain4"));
		DomainGroupConfig domain5 = new DomainGroupConfig();
		domain5.setGroup(SsoGroupTypeEnum.B);
		domain5.setDomain(PropertyConfigHolder.getProperty("domain5"));
		DomainGroupConfig domain6 = new DomainGroupConfig();
		domain6.setGroup(SsoGroupTypeEnum.B);
		domain6.setDomain(PropertyConfigHolder.getProperty("domain6"));
		groupB.add(domain4);
		groupB.add(domain5);
		groupB.add(domain6);
		domainMap.put(domain4.getDomain(), groupB);
		domainMap.put(domain5.getDomain(), groupB);
		domainMap.put(domain6.getDomain(), groupB);
	}
	
	public static List<String> getDomainGroup(String domain){
		List<String> domains = new ArrayList<>();
		List<DomainGroupConfig> groupMaps = domainMap.get(domain);
		for(DomainGroupConfig g: groupMaps){
			domains.add(g.getDomain());
		}
		return domains;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public SsoGroupTypeEnum getGroup() {
		return group;
	}

	public void setGroup(SsoGroupTypeEnum group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "DomainGroupConfig [domain=" + domain + ", group=" + group + "]";
	}

}
