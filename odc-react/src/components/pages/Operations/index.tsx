import { Card } from '@mui/material'
import { useQuery } from 'react-query'
import PageTemplate from '../../templates/PageTemplate'
import { getOperations } from '../../../shared/dataProvider/api'
import { OperationData } from '../../../shared/types/interfaces'
import DataTable from '../../organisms/DataTable'


const Operations = () => {
    const { data: operations, isFetched } = useQuery<OperationData[]>(['operationsData'], () => getOperations())

    if (!isFetched) {
        return (<h5>...is Loading</h5>)
    }
    return (
        <PageTemplate>
            <Card>
                <DataTable headerCellNames={["test1", "test2", "test3"]} />
                {operations?.map(operation => <p key={operation.id}>{operation.batchId}</p>)}
            </Card>
        </PageTemplate>
    )
}

export default Operations