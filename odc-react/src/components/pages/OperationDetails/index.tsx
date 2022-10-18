import React, { MouseEvent, useState } from 'react'
import ResponsiveAppBar from '../../organisms/ResponsiveAppBar'
import PageTemplate from '../../templates/PageTemplate'
import { useParams } from 'react-router-dom'
import OperationDetailsBodyTemplate from '../../templates/OperationDetailsBodyTemplate'
import { useQuery } from 'react-query'
import { getOperation, getOperationSteps } from '../../../shared/dataProvider/api'
import OperationInformationHeader from '../../organisms/OperationInformationHeader'
import OperationStepTable from '../../organisms/OperationStepTable'
import AddStepDialog from '../../organisms/AddStepDialog'


const OperationDetails: React.FC = () => {

    const [showDialog, setShowDialog] = useState<boolean>(false)
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
        const handleOnClickAddButton = (e: MouseEvent<HTMLButtonElement>) => {
            setShowDialog(true);
        }

        return (
            <PageTemplate header={<ResponsiveAppBar />}>
                <OperationDetailsBodyTemplate
                    detailContainer={
                        <OperationInformationHeader
                            operationData={operationData}
                            onClick={handleOnClickAddButton}

                        />
                    }
                    stepsContainer={
                        <OperationStepTable
                            data={stepData}
                        />}
                />
                <AddStepDialog
                    open={showDialog}
                    onClose={() => setShowDialog(false)}
                    defaultBatchId={operationData.batchId}
                />
            </PageTemplate>
        )
    }

    return (
        <span>Error</span>
    )
}

export default OperationDetails