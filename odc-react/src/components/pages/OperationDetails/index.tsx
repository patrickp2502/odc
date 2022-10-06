import React from 'react'
import ResponsiveAppBar from '../../organisms/ResponsiveAppBar'
import PageTemplate from '../../templates/PageTemplate'
import { useParams } from 'react-router-dom'
import OperationDetailsBodyTemplate from '../../templates/OperationDetailsBodyTemplate'

const OperationDetails: React.FC = () => {
    const operationId: string | undefined = useParams().operationId;

    return (
        <PageTemplate header={<ResponsiveAppBar />}>
            <OperationDetailsBodyTemplate
                detailContainer={<>{operationId}</>}
                stepsContainer={<></>}
            />
        </PageTemplate>

    )
}

export default OperationDetails