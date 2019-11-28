package com.pb.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pb.weixin.dao.ISongDao;
import com.pb.weixin.service.ISongService;
import com.pb.weixin.utils.Page;
import com.pb.weixin.vo.Song;

@Service
public class SongService extends BaseService<Song> implements ISongService {

	@Autowired
	private ISongDao songDao;
	
	@Override
	public List<Song> getSongsAll() {
		// TODO Auto-generated method stub
		return songDao.getSongsAll();
	}

	@Override
	public List<Song> getSongsBy(Song song) {
		// TODO Auto-generated method stub
		return songDao.getSongsBy(song);
	}

	@Override
	public int addSong(Song song) {
		// TODO Auto-generated method stub
		return songDao.addSong(song);
	}

	@Override
	public int updateSong(Song song) {
		// TODO Auto-generated method stub
		return songDao.updateSong(song);
	}

	@Override
	public int deleteSong(Song song) {
		// TODO Auto-generated method stub
		return songDao.deleteSong(song);
	}

	@Override
	public List<Song> getCollectionSongByUserId(int userId) {
		// TODO Auto-generated method stub
		return songDao.getCollectionSongByUserId(userId);
	}

	@Override
	public List<Song> getSongListBySongListId(int songListId) {
		// TODO Auto-generated method stub
		return songDao.getSongListBySongListId(songListId);
	}


	

	

}
