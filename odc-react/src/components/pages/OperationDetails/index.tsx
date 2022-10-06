import React from 'react'
import ResponsiveAppBar from '../../organisms/ResponsiveAppBar'
import PageTemplate from '../../templates/PageTemplate'
import { useParams } from 'react-router-dom'
import OperationDetailsBodyTemplate from '../../templates/OperationDetailsBodyTemplate'

interface OperationDetailsProps {
    children: React.ReactNode
}


const OperationDetails: React.FC = () => {
    const operationId: string | undefined = useParams().operationId

    return (
        <PageTemplate header={<ResponsiveAppBar />}>
            <OperationDetailsBodyTemplate
                detailContainer={<></>}
                stepsContainer={<></>}
            />
        </PageTemplate>

    )
}

export default OperationDetails