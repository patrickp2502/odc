import React from 'react'
import ResponsiveAppBar from '../../organisms/ResponsiveAppBar'
import PageTemplate from '../../templates/PageTemplate'
import { useParams } from 'react-router-dom'
import OperationDetailsBodyTemplate from '../../templates/OperationDetailsBodyTemplate'
import { useQuery } from 'react-query'
import { getOperation, getOperationSteps } from '../../../shared/dataProvider/api'
import OperationInformationHeader from '../../organisms/OperationInformationHeader'
import DataTable from '../../organisms/DataTable'
import { HeaderKeyPair } from '../../../shared/types/interfaces'


const OPERATIONSTEPS_HEADER_KEY_TEMPLATE: HeaderKeyPair[] = [
    { key: "category", headerName: "Category" },
    { key: "operatorName", headerName: "Operator" },
    { key: "startTime", headerName: "Start" },
    { key: "stopTime", headerName: "Ende" },
    { key: "status", headerName: "Status" }
]

const OperationDetails: React.FC = () => {
    const operationId: string | undefined = useParams().operationId;
    const {
        data: operationData,
        isFetched: operationDataIsFetched,
        isIdle: operationDataIsIdle,
        isSuccess: operationDataFetchIsSuccess
    } = useQuery(['operation'],
        () => getOperation(operationId))


    const {
        data: stepData,
        isFetched: stepDataIsFetched,
        isIdle: stepDataIsIdle,
        isSuccess: stepDataFetchIsSuccess
    } = useQuery(["operationSteps"],
        () => getOperationSteps(operationId))


    if (!operationDataIsFetched ||
        operationDataIsIdle ||
        !stepDataIsFetched ||
        stepDataIsIdle) {
        return (
            <span>Loading</span>
        )
    }
    if (stepDataFetchIsSuccess && operationDataFetchIsSuccess) {

        return (
            <PageTemplate header={<ResponsiveAppBar />}>
                <OperationDetailsBodyTemplate
                    detailContainer={
                        <OperationInformationHeader
                            operationData={operationData} />
                    }
                    stepsContainer={
                        <DataTable
                            data={stepData}
                            headerKeyTemplate={OPERATIONSTEPS_HEADER_KEY_TEMPLATE} />}
                />
            </PageTemplate>
        )
    }

    return (
        <span>Error</span>
    )
}

export default OperationDetails