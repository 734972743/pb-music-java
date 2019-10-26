package com.pb.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pb.weixin.dao.ISongListDao;
import com.pb.weixin.service.ISongListService;
import com.pb.weixin.vo.SongList;

@Service
public class SongListService implements ISongListService {
	
	@Autowired
	private ISongListDao iSongListDao;

	@Override
	public List<SongList> getSongListsBy(SongList songList) {
		// TODO Auto-generated method stub
		return iSongListDao.getSongListsBy(songList);
	}

	@Override
	public int addSongList(SongList songList) {
		// TODO Auto-generated method stub
		return iSongListDao.addSongList(songList);
	}

	@Override
	public int deleteSongList(SongList songList) {
		// TODO Auto-generated method stub
		return iSongListDao.deleteSongList(songList);
	}

	@Override
	public int updateSongList(SongList songList) {
		// TODO Auto-generated method stub
		return iSongListDao.updateSongList(songList);
	}

	@Override
	public List<SongList> getgetSongListsByUserId(int userId) {
		// TODO Auto-generated method stub
		return iSongListDao.getgetSongListsByUserId(userId);
	}

}
