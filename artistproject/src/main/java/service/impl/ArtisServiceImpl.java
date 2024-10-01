package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.Artis;
import dao.impl.ArtisDaoImpl;
import service.ArtisService;

@Service
public class ArtisServiceImpl implements ArtisService {

	@Autowired
	ArtisDaoImpl adi;// 用spring管理

	@Override
	public void create(String artName) {
		adi.create(artName);
	}

	@Override
	public List<Artis> selectAll() {
		List<Artis> artisList = adi.selectAll();
		return artisList;
	}

	@Override
	public Artis selectByArtisId(String artId) {
		List<Artis> artisList = adi.selectAll();
		artisList.stream().filter(a->a.getArtisId().equals(artId));
		return artisList.get(0);
	}

	@Override
	public Artis selectByArtisName(String artName) {
		List<Artis> artisList = adi.selectAll();
		artisList.stream().filter(a->a.getArtisName().equals(artName));
		return artisList.get(0);
	}

	@Override
	public void update(String artId, String artName) {
		Artis art = new Artis(artId, artName);
		adi.update(art);

	}

	@Override
	public void delete(String artId) {
		adi.delete(artId);
	}

}
