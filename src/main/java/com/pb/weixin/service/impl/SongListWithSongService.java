package com.pb.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pb.weixin.dao.ISongListWithSongDao;
import com.pb.weixin.service.ISongListWithSongService;
import com.pb.weixin.vo.SongListWithSong;

@Service
public class SongListWithSongService implements ISongListWithSongService {
	
	@Autowired
	private ISongListWithSongDao iSongListWithSongDao;

	@Override
	public List<SongListWithSong> getSongListWithSongBy(SongListWithSong songListWithSong) {
		// TODO Auto-generated method stub
		return iSongListWithSongDao.getSongListWithSongBy(songListWithSong);
	}

	@Override
	public int addSongListWithSong(SongListWithSong songListWithSong) {
		// TODO Auto-generated method stub
		return iSongListWithSongDao.addSongListWithSong(songListWithSong);
	}

	@Override
	public int deleteSongListWithSong(SongListWithSong songListWithSong) {
		// TODO Auto-generated method stub
		return iSongListWithSongDao.deleteSongListWithSong(songListWithSong);
	}

	@Override
	public int updateSongListWithSong(SongListWithSong songListWithSong) {
		// TODO Auto-generated method stub
		return iSongListWithSongDao.updateSongListWithSong(songListWithSong);
	}

}
