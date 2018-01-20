package com.wlgdo.apartment.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlgdo.apartment.domain.Owner;
import com.wlgdo.apartment.mapper.OwnerMapper;
import com.wlgdo.common.utils.ExcelUtils;

@Service
public class OwnerService {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private OwnerMapper ownerMapper;

	public int save(Owner user) {
		return ownerMapper.save(user);
	}

	public List<Owner> query(Owner user) {
		return ownerMapper.query(user);
	}

	public Owner update(Owner owner) {
		owner.setBuild("6");
		ownerMapper.update(owner);
		if (1 == ownerMapper.update(owner)) {
			return owner;
		}
		return null;
	}

	public int importOwner(String userid, String build) {
		File file = new File("e://t1.xls");
		int columnNumber = 2;
		Owner owner = null;
		try {
			String[][] list = ExcelUtils.getExcelData(file, columnNumber);
			log.info("获取到的用户列表：{}", list);
			for (int i = 1; i < list.length; i++) {
				owner = new Owner(list[i][0], "0.00", "0.00");
				owner.setName(list[i][1]);
				owner.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				owner.setBuild(build);
				save(owner);
			}
			return list.length - 1;
		} catch (Exception e) {
			log.error("导入失败：{},{}", owner, e);
		}
		return 0;
	}

	public int importFeeData(File file, String feetype) {
		try {
			String[][] list = ExcelUtils.getExcelData(file, 3);
			log.info("获取到的用户列表：{}", list);
			for (int i = 1; i < list.length; i++) {
				update(new Owner(list[i][0], list[i][1], list[i][2]));
			}
			return list.length - 1;
		} catch (Exception e) {
			log.error("导入失败：{},{}", e);
		}
		return 0;
	}

}
