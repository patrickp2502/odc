import React from 'react'
import ResponsiveAppBar from '../../organisms/ResponsiveAppBar'
import PageTemplate from '../../templates/PageTemplate'
import { useParams } from 'react-router-dom'
import OperationDetailsBodyTemplate from '../../templates/OperationDetailsBodyTemplate'
import { useQuery } from 'react-query'
import { getOperation } from '../../../shared/dataProvider/api'
import OperationInformationHeader from '../../organisms/OperationInformationHeader'
import DataTable from '../../organisms/DataTable'
import { HeaderKeyPair } from '../../../shared/types/interfaces'

const OPERATIONSTEPS_HEADER_KEY_TEMPLATE: HeaderKeyPair[] = [
    { key: "batchId", headerName: "Batch" },
    { key: "startTime", headerName: "Start" },
    { key: "stopTime", headerName: "Ende" },
    { key: "status", headerName: "Status" }
]




const OperationDetails: React.FC = () => {
    const operationId: string | undefined = useParams().operationId;
    const {
        data: operationData,
        isFetched,
        isIdle,
        isSuccess
    } = useQuery(['operation'],
        () => getOperation(operationId))

    if (!isFetched || isIdle) {
        return (
            <span>Loading</span>
        )
    }
    if (isSuccess) {

        return (
            <PageTemplate header={<ResponsiveAppBar />}>
                <OperationDetailsBodyTemplate
                    detailContainer={
                        <OperationInformationHeader
                            operationData={operationData} />
                    }
                    stepsContainer={
                        <DataTable
                            data={operationData.steps}
                            headerKeyTemplate= />}
                />
            </PageTemplate>
        )
    }

    return (
        <span>Error</span>
    )
}

export default OperationDetails