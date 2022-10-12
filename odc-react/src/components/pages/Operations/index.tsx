import { Card, CircularProgress } from '@mui/material'
import { useQuery } from 'react-query'
import PageTemplate from '../../templates/PageTemplate'
import { getOperations } from '../../../shared/dataProvider/api'
import ResponsiveAppBar from '../../organisms/ResponsiveAppBar'
import OperationPageTemplate from '../../templates/OperationPageTemplate'
import OperationDataTable from '../../organisms/OperationDataTable'
import { OperationData } from '../../../shared/types/interfaces'


const Operations = () => {
    const { data, isFetched } = useQuery<OperationData[]>(
        ['operationsData'], () => getOperations())


    if (!isFetched) {
        return (
            <PageTemplate header={<ResponsiveAppBar />}>
                <Card>
                    <CircularProgress />
                </Card>
            </PageTemplate >
        )
    }

    const operations = data ?? []

    return (
        <OperationPageTemplate header={<ResponsiveAppBar />}>
            <OperationDataTable
                data={operations}
            />
        </OperationPageTemplate >
    )
}

export default Operations