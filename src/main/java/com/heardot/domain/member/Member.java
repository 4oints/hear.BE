package com.heardot.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.heardot.domain.member.constant.Role;
import com.heardot.domain.memberToken.MemberToken;
import com.heardot.domain.posting.Posting;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@ToString(exclude = {"memberToken","postings"})
@AllArgsConstructor @NoArgsConstructor
@SQLDelete(sql = "UPDATE member SET is_delete = true WHERE member_id=?")
@Where(clause = "is_delete=false")
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberId;

    @Column(nullable = false)
    private String memberName;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @JsonIgnore
    private boolean isDelete = false;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private MemberToken memberToken;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Posting> postings;

    @JsonIgnore
    public String getRoleKey() {
        return this.role.getKey();
    }

    public void updateMemberToken(MemberToken memberToken) {
        this.memberToken = memberToken;
    }

}
