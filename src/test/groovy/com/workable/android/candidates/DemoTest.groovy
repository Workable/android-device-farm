package com.workable.android.candidates

import com.workable.android.base.DeviceFarmTestBase
import com.workable.android.base.annotations.Info
import com.workable.android.businessObjects.candidates.CandidatesListBusinessObject
import com.workable.android.dtos.users.SignUpData
import com.workable.android.enums.candidate.CandidateField
import com.workable.android.enums.candidate.ShowCandidates
import com.workable.android.enums.common.Direction
import com.workable.android.navigation.Navigate
import com.workable.android.setup.dataLoaders.LoadData
import org.springframework.beans.factory.annotation.Autowired
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

/**
 * Created by panagiotis_tsiakos on 10/16/15.
 */
@Info(description = 'This test case verifies the candidate by job list filtering functionality')
public class DemoTest extends DeviceFarmTestBase {

    @Autowired
    Navigate navigate

    @Autowired
    CandidatesListBusinessObject candidatesListBusinessObject

    SignUpData signUpData
    String job1 = 'QA Engineer'
    List<String> qaCandidates = ['Rebecca Sutte', 'Paige McCullers', 'Phillip Jessup', 'Emily Sinclair', 'Caleb Hapstall', 'Levi Sutter', 'Byron Montgomery', 'Caleb Rivers', 'Ella Montgomery', 'Pam Fields']

    @BeforeClass
    void createPreconditions() throws Exception {
        signUpData = LoadData.loadSignUpData()
        navigate.toCandidatesList(signUpData, job1)
    }

    @Test(description = 'Candidates filter by job', priority = 1, alwaysRun = true)
    void testStep1() {
        candidatesListBusinessObject.filterBy(ShowCandidates.ShowDisqualified)
        candidatesListBusinessObject.verifyNumberOfCandidates(1)
        candidatesListBusinessObject.verifyCandidateName('Lila', 'Stangard')
        candidatesListBusinessObject.verifyCandidateHeadline('Lila', 'Stangard', job1)
        candidatesListBusinessObject.verifyCandidateAvatar('Lila', 'Stangard')
        candidatesListBusinessObject.getSingleActionsOnPage().pressCandidateFilters()
        candidatesListBusinessObject.verifyFilterByCandidateTypeIsSelected(ShowCandidates.ShowDisqualified)
        candidatesListBusinessObject.verifyFilterByCandidateTypeIsNotSelected(ShowCandidates.ShowUnread)
        candidatesListBusinessObject.getSingleActionsOnPage().pressResetFilters()
        ShowCandidates.values().each { option ->
            candidatesListBusinessObject.verifyFilterByCandidateTypeIsNotSelected(option)
        }
        candidatesListBusinessObject.getSingleActionsOnPage().pressDoneFilter()
        qaCandidates.each { candidate ->
            candidatesListBusinessObject.scrollToCandidateField(CandidateField.Name, candidate.split(' ')[0], candidate.split(' ')[1], Direction.Down)
            candidatesListBusinessObject.verifyCandidateName(candidate.split(' ')[0], candidate.split(' ')[1])
        }
        candidatesListBusinessObject.filterBy(ShowCandidates.ShowUnread)
        candidatesListBusinessObject.verifyBlankSlateLabel()
        candidatesListBusinessObject.verifyBlankSlateTitle()
    }

}
