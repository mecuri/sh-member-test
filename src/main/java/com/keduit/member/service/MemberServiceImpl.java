package com.keduit.member.service;

import org.springframework.stereotype.Service;

import com.keduit.member.dto.MemberDTO;
import com.keduit.member.entity.Member;
import com.keduit.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository memberRepository;

	@Override
	public String register(MemberDTO memberDTO) {
		
		log.info("dto -----------------------" + memberDTO);
		
		Member member = dtoToEntity(memberDTO);
		
		memberRepository.save(member);
		
		return member.getId();
	}

	@Override
	public MemberDTO get(String id) {
		
		Object result = memberRepository.getMemberById(id);
		
		return entityToDTO((Member) result);
	}

	@Override
	public void remove(String id) {

		memberRepository.deleteById(id);
		
	}

	@Override
	public void modify(MemberDTO memberDTO) {
		
		Member member = memberRepository.findById(memberDTO.getId()).get();
		
		if(member != null) {
			member.change(memberDTO.getPass(),
							memberDTO.getNickname(),
							memberDTO.getImage(),
							memberDTO.getName(),
							memberDTO.getAge(),
							memberDTO.getAddress(),
							memberDTO.getPhone(),
							memberDTO.getIntroduce());
		}
		
	}

}
