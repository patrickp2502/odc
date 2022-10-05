import { Card } from '@mui/material'
import { useQuery } from 'react-query'
import PageTemplate from '../../templates/PageTemplate'
import { getOperations } from '../../../shared/dataProvider/api'
import { HeaderKeyPair } from '../../../shared/types/interfaces'
import ResponsiveAppBar from '../../organisms/ResponsiveAppBar'
import DataTable from '../../organisms/DataTable'

const OPERATION_HEADER_KEY_TEMPLATE: HeaderKeyPair[] = [
    { key: "id", headerName: "ID" },
    { key: "batchId", headerName: "Batch" },
    { key: "startTime", headerName: "Start" },
    { key: "stopTime", headerName: "Ende" },
    { key: "status", headerName: "Status" }
]

const Operations = () => {
    const { data, isFetched } = useQuery<Record<string, any>[]>(['operationsData'], () => getOperations())

    if (!isFetched) {
        return (
            <PageTemplate header={<ResponsiveAppBar />}>
                <Card>
                    ... is loading
                </Card>
            </PageTemplate >

        )
    }

    const operations = data ?? []

    return (
        <PageTemplate header={<ResponsiveAppBar />}>
            <DataTable data={operations} headerKeyTemplate={OPERATION_HEADER_KEY_TEMPLATE} />
            <Card>
                {operations?.map(operation =>
                    <p key={operation.id}>{operation.batchId}</p>)}
            </Card>
        </PageTemplate >
    )
}

export default Operations