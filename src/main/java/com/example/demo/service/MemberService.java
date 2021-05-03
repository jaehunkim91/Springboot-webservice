package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	// 회원 가입
	@Transactional
	public Long join(Member member) {
		vaildateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}
	

	private void vaildateDuplicateMember(Member member) {
		List<Member> findMember = memberRepository.findByName(member.getUserName());
		if(!findMember.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}

	// 회원 전체 조회
	private List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	// 회원 전체 조회
	private Member findOne(long memberId){
		return memberRepository.find(memberId);
	}
	
}
