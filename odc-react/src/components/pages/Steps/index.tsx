import ResponsiveAppBar from '../../organisms/ResponsiveAppBar'
import SubNav from '../../organisms/SubNav'
import PageTemplate from '../../templates/PageTemplate'
import StepBodyTemplate from '../../templates/StepBodyTemplate'

const Steps = () => {
    return (
        <PageTemplate header={<ResponsiveAppBar />}>
            <StepBodyTemplate
                subnav={<SubNav />}
                leftContainer={<div></div>}
                rightContainer={<div></div>}
            />
        </PageTemplate>
    )
}



export default Steps