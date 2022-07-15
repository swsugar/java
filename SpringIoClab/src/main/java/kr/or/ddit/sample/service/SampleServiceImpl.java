package kr.or.ddit.sample.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Qualifier;
import javax.sound.midi.MidiDevice.Info;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import kr.or.ddit.sample.dao.SampleDAO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SampleServiceImpl implements SampleService{
	
	@Inject
	private ApplicationContext context;
//	1. new insetance 직접 샏ㅇ성하고, 의존관계 형성. - 결합력 최상.
//	private SampleDao sampleDAO = new SampleDAO_Maria();
//  private SampleDao sampleDAO = new SampleDAO_Maria();


//   2. Factory  Object Pattern : 결합력 잔존
//	private SampleDAO sampleDAO = new SampleDAOFactory().getSampelDAO();
	
//	3. Strategy Pattern : 전략주입자가 모든 결합력 갖게됨.
	private SampleDAO sampleDAO;
	
	
	public SampleServiceImpl() {
		super();
		log.info("{} 객체 생성_기본 생성자", this.getClass());
		log.info("컨테이너 hashcode : {}", context.hashCode());
	}
	
	@PostConstruct // 모든 의존성 주입 끝난 이후에 동작
	public void init() {
		log.info("{} 객체 초기화", this.getClass());
	}
	
//	@Autowired
	@Inject
	@Named("sampleDAO_Maria")
	public SampleServiceImpl(SampleDAO sampleDAO) {
		super();
		this.sampleDAO = sampleDAO;
		log.info("{} 객체 생성_dao 주입 생성자", this.getClass());
	}

	@Required
	@Resource(name="sampleDAO_Maria")
	public void setSampleDAO(SampleDAO sampleDAO) {
		this.sampleDAO = sampleDAO;
		log.info("setter 주입을 통해 {}를 주입합.",sampleDAO.getClass());
	}


@Override
public StringBuffer retriveSampleData(String code) {
	String rawData = sampleDAO.selectSampleData(code);
	StringBuffer info = new StringBuffer(rawData);
	return info;
}


}
